package com.example.myapplication;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class dataClass {


    public ArrayList<lessonClass> createData() {
        lessonClass lesson1 = new lessonClass(1,"درس 1", false, "مقدمات جاوا و آشنایی", "جاوا یک زبان برنامه\u200Cنویسیِ شیءگرا است که نخستین بار توسط جیمز گاسلینگ در شرکت سان\u200Cمایکروسیستمز ایجاد گردید و در سال ۱۹۹۱ به عنوان بخشی از سکوی جاوا منتشر شد. زبان جاوا شبیه به سی\u200Cپلاس\u200Cپلاس است؛ اما مدل شیءگرایی آسان\u200Cتری دارد و از قابلیت\u200Cهای سطح پایین کمتری پشتیبانی می\u200Cکند. ", "test",R.mipmap.pic1, R.raw.vid1);
        lessonClass lesson2 = new lessonClass(2,"درس 2", false, "مفاهیم اولیه در جاوا", "برای هر نوع برنامه\u200Cای، کدهای جاوا در فایل\u200Cهای متنی با پسوند «java.» نوشته می\u200Cشوند که به آن\u200Cها «سورس فایل\u200Cهای جاوا» (Java Source Files) می\u200Cگویند. این سورس فایل\u200Cها، با استفاده از مترجم یا «کامپایلر» (Compiler) جاوا، به «فایل\u200Cهای کلاس» (Class Files) در این زبان کامپایل می\u200Cشوند.سپس، فایل\u200Cهای کلاس، درون آرشیوهای «ZIP» کنار یکدیگر قرار می\u200Cگیرند که به آن\u200Cها، «فایل\u200Cهای جار» (JAR Files) می\u200Cگویند. در مرحله بعد، این فایل\u200Cهای جار به ماشین مجازی جاوا داده شده و با استفاده از تابع «()main» در یک کلاس مشخص اجرا می\u200Cشوند.متغیر (Variable) ...\n" +
                "نوع (Type) ...\n" +
                "کلاس (Class) ...\n" +
                "شی\u200Cء (Object) ...\n" +
                "سازنده (Constructor) ...\n" +
                "متد (Method) ...\n" +
                "فیلد (Field)", "test",R.mipmap.pic2, R.raw.vid1);
        lessonClass lesson3 = new lessonClass(3,"درس 3", false, "شئی گرایی در جاوا", "شی گرایی در برنامه نویسی روشی جهت طراحی برنامه\u200Cها با استفاده از کلاس ها و اشیا به حساب می\u200Cآید. اهمیت شی گرایی در جاوا به گونه\u200Cای است که این رویکرد برنامه نویسی به عنوان هسته جاوا شناخته می\u200Cشود. شی گرایی اشیا را در برنامه نویسی سازماندهی و واسط در جاوا را به خوبی تعریف می\u200Cکند. این روش به عنوان روشی جهت کنترل داده\u200Cها در کدها نیز مورد استفاده قرار می\u200Cگیرد.", "test" ,R.mipmap.pic3, R.raw.vid1);

        lessonClass lesson4 = new lessonClass(4,"درس 4",false,"test","test","test",R.mipmap.pic1,R.raw.vid1);
        lessonClass lesson5 = new lessonClass(5,"درس5",false,"test","test","test",R.mipmap.pic1,R.raw.vid1);

        ArrayList<lessonClass> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
        lessons.add(lesson5);
        return lessons;

    }
}

