package com.webapp.base.utils;

import java.util.UUID;

public class StringEx {
	
	public static final String newUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
