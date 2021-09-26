package com.tomseiler.mudproxy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ansi {
    // https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797

    public static final String ESCAPE = "\033";
    private static final Pattern ESCAPE_PATTERN = Pattern.compile("\033\\[.*?m");

    public static final String DEFAULT = "\033[39m";
    public static final String RESET = "\033\\[0m";

    public static final String BLACK = "\033[30m";
    public static final String BOLD_BLACK = "\033[1;30m";
    public static final String RED = "\033[31m";
    public static final String BOLD_RED = "\033[1;31m";
    public static final String GREEN = "\033[32m";
    public static final String BOLD_GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[33m";
    public static final String BOLD_YELLOW = "\033[1;33m";
    public static final String BLUE = "\033[34m";
    public static final String BOLD_BLUE = "\033[1;34m";
    public static final String MAGENTA = "\033[35m";
    public static final String BOLD_MAGENTA = "\033[1;35m";
    public static final String CYAN = "\033[36m";
    public static final String BOLD_CYAN = "\033\\[1;36m";
    public static final String WHITE = "\033[37m";
    public static final String BOLD_WHITE = "\033[1;37m";

    public static String stripAnsi(String s) {
        Matcher matcher = ESCAPE_PATTERN.matcher(s);
        if (matcher.find()) {
            return matcher.replaceAll("");
        }
        return s;
    }


}
