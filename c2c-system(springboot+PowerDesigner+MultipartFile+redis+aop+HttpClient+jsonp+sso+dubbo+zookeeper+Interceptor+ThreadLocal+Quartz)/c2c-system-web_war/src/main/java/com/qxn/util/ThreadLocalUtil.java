package com.qxn.util;

import com.qxn.pojo.User;
	public class ThreadLocalUtil {
	private static ThreadLocal<User> thread = new ThreadLocal<User>();
	public static void set(User user) {
		thread.set(user);
	}
	public static User get() {
		return thread.get();
	}
	//防止内存泄漏
	public static void remove() {
		thread.remove();
	}
}
