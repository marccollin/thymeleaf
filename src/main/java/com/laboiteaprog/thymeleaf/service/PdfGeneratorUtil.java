/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboiteaprog.thymeleaf.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author collinm
 */
@Component
public class PdfGeneratorUtil<T> {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] process(String templateName, String templateExtension, List<T> listT, String contextVariableName) throws Exception {

        Context ctx = new Context();

        ctx.setVariable(contextVariableName, listT);

        String processedHtml = templateEngine.process("fragments/html-reports/" + templateName + "." + templateExtension, ctx);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        PdfRendererBuilder builder = new PdfRendererBuilder();

        builder.useFastMode();

        builder.withHtmlContent(processedHtml, "");

        builder.toStream(output);

        builder.run();

        return output.toByteArray();

    }

}
