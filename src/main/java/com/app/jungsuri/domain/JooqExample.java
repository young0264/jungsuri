package com.app.jungsuri.domain;

import org.jooq.DSLContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JooqExample implements CommandLineRunner {

    private final DSLContext create;

    public JooqExample(DSLContext dslContext) {
        this.create = dslContext;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(create.select().from("post").fetch());
    }
}
