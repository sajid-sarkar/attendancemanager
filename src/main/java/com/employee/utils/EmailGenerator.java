package com.employee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public final class EmailGenerator {

    private static final String USER = "$username$";
    private static final String LINK = "$link$";
    private static final String ADMIN_Email = "$admin_mail$";

    public static String generateMailWithUsername(final String username, final String link, final String emailTemplate) {
        return getMailTemplate(emailTemplate)
            .toString()
            .replace(USER, username)
            .replace(LINK, link);

    }

    private static StringBuilder getMailTemplate(String emailTemplate) {
        BufferedReader bufferedReader = new BufferedReader(getFileReader(emailTemplate));
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private static Reader getFileReader(final String fileName) {
        return new InputStreamReader(EmailGenerator.class.getResourceAsStream(fileName), StandardCharsets.UTF_8);
    }

}
