package com.alternativepayments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for sample application.
 */
@Controller
public class IndexController {


    /**
     * Root controller showing all menu options..
     *
     * @return index view.
     */
    @RequestMapping("/")
    public String index() {
        return "static/index";
    }

}
