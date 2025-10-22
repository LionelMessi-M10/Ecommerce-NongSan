package com.multishop.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.multishop.entity.Order;
import com.multishop.service.InvoicePdfService;

import jakarta.mail.internet.MimeMessage;

@Service
public class InvoicePdfServiceImpl implements InvoicePdfService {
	
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

	@Override
	public File generateInvoicePdf(Order order) throws Exception {
		String filename = "invoice_" + order.getId() + ".pdf";
        File file = new File(System.getProperty("java.io.tmpdir"), filename);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        document.add(new Paragraph("HÓA ĐƠN MUA HÀNG", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        document.add(new Paragraph("Mã đơn hàng: " + order.getId()));
        document.add(new Paragraph("Ngày đặt: " + order.getCreatedDate()));
        document.add(new Paragraph("Khách hàng: " + order.getUser().getUserName()));
        document.add(new Paragraph("Tổng tiền: " + order.getFinalAmount() + " VND"));
        document.add(new Paragraph("Cảm ơn bạn đã mua hàng tại MultiShop!"));

        document.close();
        return file;
	}

	@Override
	public void sendOrderEmailWithPdf(Order order, File pdfInvoice) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        helper.setTo(order.getUser().getEmail());
        helper.setSubject("Xác nhận đơn hàng #" + order.getId());
        helper.setFrom("your-email@gmail.com");

        Context context = new Context();
        context.setVariable("orderId", order.getId());
        context.setVariable("orderDate", order.getCreatedDate());
        context.setVariable("total", order.getFinalAmount());

        String htmlContent = templateEngine.process("web/invoice", context);
        helper.setText(htmlContent, true);

        helper.addAttachment("HoaDon-MultiShop.pdf", pdfInvoice);

        mailSender.send(message);
	}

}
