package com.mowitnow;

import com.mowitnow.controller.IMower;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Max Velasco <ivan-maximino.velasco-martin@ign.fr>
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/main-context.xml"});
        IMower mower = (IMower) context.getBean("mower");
        System.out.println("... Start mowing ...");
        mower.mow();
        System.out.println("... Finished ...");
    }
}
