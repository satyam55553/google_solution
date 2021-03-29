package com.example.gsc;

public class VideoData {
    public String url, key;

    public VideoData() {
    }

    public VideoData(String key, String url) {
        this.key = key;
        this.url = url;
    }

    public static String createKey(String university, String course, String branch,
                                   int semester, String subject, String topicCode) {
        StringBuilder key = new StringBuilder();
        switch (university) {
            case "Mumbai University":
                key.append("MU");
                break;
            case "Delhi University":
                key.append("DU");
                break;
            case "Gujarat University":
                key.append("GU");
                break;
            case "Pune University":
                key.append("PU");
                break;
            default:
                key.append("XX");
        }
        switch (course) {
            case "Bachelor of Engineering (B.E.)":
                key.append("BE");
                break;
            default:
                key.append("XX");
        }
        switch (branch) {
            case "Computer":
                key.append("CO");
                break;
            case "IT":
                key.append("IT");
                break;
            case "Electronics & Telecommunication":
                key.append("ET");
                break;
            case "Electronics":
                key.append("EL");
                break;
            case "Instrumentation":
                key.append("IN");
                break;
            default:
                key.append("XX");
        }
        switch (semester) {
            case 1:
                key.append("1");
                break;
            case 2:
                key.append("2");
                break;
            case 3:
                key.append("3");
                break;
            case 4:
                key.append("4");
                break;
            case 5:
                key.append("5");
                break;
            case 6:
                key.append("6");
                break;
            case 7:
                key.append("7");
                break;
            case 8:
                key.append("8");
                break;
            default:
                key.append("X");
        }
        switch (subject) {
            case "Embedded System & Real Time Operating System":
                key.append("1");
                break;
            case "Sub 2":
                key.append("2");
                break;
            case "Sub 3":
                key.append("3");
                break;
            case "Sub 4":
                key.append("4");
                break;
            case "Sub 5":
                key.append("5");
                break;
            case "Sub 6":
                key.append("6");
                break;
            default:
                key.append("X");
        }
        key.append(topicCode);
        return key.toString();
    }

    public String getUrl() {
        return this.url;
    }
    public String getKey() {
        return this.key;
    }

}
