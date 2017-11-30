package com.cts.qbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.qbank.service.TransactionService;

@Controller
public class ExportController {

@Autowired
TransactionService txService;

/**
 * Handle request to download an Excel document
 */
@RequestMapping(value = "/download", method = RequestMethod.GET)
public String download(Model model) {
    model.addAttribute("tx", txService.findPrimaryTransactionList(""));
    return "";
}
}