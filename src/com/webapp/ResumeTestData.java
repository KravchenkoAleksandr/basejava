package com.webapp;

import com.webapp.model.*;

import java.time.LocalDate;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(List.of("""
                Организация команды и успешная реализация Java проектов для сторонних заказчиков:
                приложения автопарк на стеке Spring Cloud/микросервисы,\
                система мониторинга показателей спортсменов на Spring Boot,
                участие в проекте МЭШ на Play-2, многомодульный Spring Boot +\
                Vaadin проект для комплексных DIY смет""", "С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\"," + " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP).\n Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.")));

        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(List.of("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy")));

        resume.addSection(SectionType.EXPERIENCE, new CompanySection(List.of(new Company("Java Online Projects", "http://javaops.ru/", List.of(new Company.Period(LocalDate.of(2013, 10, 1), null,
                "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."))))));

        resume.addSection(SectionType.EDUCATION, new CompanySection(List.of(new Company("Coursera", "https://www.coursera.org/course/progfun",
                List.of(new Company.Period(LocalDate.of(2013, 3, 1),
                        LocalDate.of(2013, 5, 1), "", "\n" +
                        "'Functional Programming Principles in Scala' by Martin Odersky"))))));

        printResume(resume);
    }

    private static void printResume(Resume resume) {
        System.out.println("Резюме: " + resume.getFullName());

        System.out.println("\nКонтакты:");
        for (ContactType type : resume.getContacts().keySet()) {
            System.out.println(type.getValue() + ": " + resume.getContacts().get(type));
        }

        System.out.println("\nСекции:");
        for (SectionType type : resume.getSections().keySet()) {
            System.out.println(type.getTitle() + ":\n" + resume.getSections().get(type));
        }
    }
}
