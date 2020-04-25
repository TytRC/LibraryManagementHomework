package com.example.librarytest.support;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final Library library = new Library();
    private static final List<Borrower> borrowers = new ArrayList<>();
    private static final String[] initBorrower = new String[]{
            "Rain Chen,19030500217",
            "Manqi Chen,19030100321",
            "Jiajia Shi,19030500216",
            "Bamboo Yao,19030100531",
    };

    private static final String[] initBook = new String[]{
            "编写高质量代码：改善JavaScript程序的188个建议",
            "JavaScript语言精髓与编程实践（第2版）",
            "测试驱动的JavaScript开发",
            "JavaScript设计模式",
            "JavaScript模式",
            "高性能JavaScript",
            "JavaScript & DHTML Cookbook(中文版)",
            "深入浅出CoffeeScript",
            "JavaScript高效图形编程",
            "JavaScript语言精粹(修订版)",
            "征服JavaScript 高级程序设计与应用实例",
            "锋利的jQuery",
            "Dojo权威指南",
            "Dojo构建Ajax应用程序",
            "Node Web开发",
            "单页Web应用 JavaScript前端到后端 Web开发指南",
            "数据结构与算法JavaScript描述",
            "深入浅出Node.js",
            "Node.js 实战",
            "JavaScript异步编程：设计快速响应的网络应用",
            "JavaScript面向对象编程指南",
            "ActionScript"
    };

    static {
        for (String str : initBorrower){
            String[] msg = str.split(",");
            Borrower borrower = new Borrower(msg[0],msg[1]);
            borrowers.add(borrower);
        }
        for (String str : initBook){
            Book book = new Book(str, null, null);
            library.addBook(book);
        }
    }

    public static List<Borrower> getBorrowers() {
        return borrowers;
    }

    public static Library getLibrary() {
        return library;
    }
}
