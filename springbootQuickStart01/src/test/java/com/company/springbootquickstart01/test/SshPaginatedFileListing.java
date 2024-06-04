package com.company.springbootquickstart01.test;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SshPaginatedFileListing {

    public static void main(String[] args) {
        String host = "192.168.30.122";
        String user = "root";
        String password = "1q2w3e4r";
        String directory = "/opt/module/clash/clash-for-linux-backup-main"; // 更改为你想要列出的目录
        int pageSize = 2; // 每页显示的文件数
        int pageNumber = 2; // 页码，从1开始

        List<FileInfo> allFiles = getDirectoryList(host, user, password, directory);
        if (allFiles == null) {
            System.err.println("Failed to retrieve directory list.");
            return;
        }

        // 按更新时间降序排列
        Collections.sort(allFiles, Comparator.comparing(FileInfo::getLastModified).reversed());

        // 获取指定页码的文件列表
        List<FileInfo> pageFiles = getPage(allFiles, pageNumber, pageSize);
        pageFiles.forEach(System.out::println);
    }

    private static List<FileInfo> getDirectoryList(String host, String user, String password, String directory) {
        List<FileInfo> files = new ArrayList<>();

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("ls -l --time-style=+'%Y-%m-%d %H:%M:%S' " + directory); // 使用自定义时间格式
            channelExec.setInputStream(null);
            InputStream inputStream = channelExec.getInputStream();

            channelExec.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("total")) {
                    FileInfo fileInfo = parseFileInfo(line, directory);
                    if (fileInfo != null && !"directory".equals(fileInfo.getType())) {
                        files.add(fileInfo);
                    }
                }
            }

            channelExec.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return files;
    }

    private static FileInfo parseFileInfo(String line, String directory) throws ParseException {
        String[] parts = line.split("\\s+");
        if (parts.length < 3) return null;
//        if (parts.length < 9) return null;

        String permissions = parts[0];
        long size = Long.parseLong(parts[4]);
        String date = parts[5] + " " + parts[6];
        String name = line.substring(line.indexOf(parts[7]));
//        String name = line.substring(line.indexOf(parts[8]));

        String type = permissions.startsWith("d") ? "directory" : "file";
        if ("directory".equals(type)) return null;

        double sizeMB = size / (1024.0 * 1024.0);
        String sizeMBStr = String.format("%.2f MB", sizeMB);
        if ("0.00 MB".equals(sizeMBStr)) {
            sizeMBStr = "0.01 MB";
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date lastModified = dateFormat.parse(date);

        return new FileInfo(name, sizeMBStr, type, date);
    }

    private static List<FileInfo> getPage(List<FileInfo> files, int pageNumber, int pageSize) {
        return files.stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    static class FileInfo {
        private String name;
        private String sizeMB;
        private String type;
        private String lastModified;

        public FileInfo(String name, String sizeMB, String type, String lastModified) {
            this.name = name;
            this.sizeMB = sizeMB;
            this.type = type;
            this.lastModified = lastModified;
        }

        public String getName() {
            return name;
        }

        public String getSizeMB() {
            return sizeMB;
        }

        public String getType() {
            return type;
        }

        public String getLastModified() {
            return lastModified;
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Size: %s, Type: %s, Last Modified: %s",
                    name, sizeMB, type, lastModified);
        }
    }
}


