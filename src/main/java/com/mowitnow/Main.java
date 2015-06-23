package com.mowitnow;

import com.mowitnow.controller.IMower;
import com.mowitnow.input.IConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * @author Max Velasco <ivan-maximino.velasco-martin@ign.fr>
 */
public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/main-context.xml"});

            //Ou on peut le passer par paramètre, demander à l'utilisateur, etc...
            Resource dataFile = context.getResource("classpath:input.txt");
            IConfigReader reader = (IConfigReader) context.getBean("reader");
            reader.loadData(dataFile.getInputStream());
            IMower mower = (IMower) context.getBean("mower");

            System.out.println("... Start mowing ...");
            mower.mow();
            System.out.println("... Finished ...");
        } catch (Throwable e) {
            LOGGER.error("Error while mowing ", e);
        }

    }
}
