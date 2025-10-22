package com.multishop.service;

import java.io.File;

import com.multishop.entity.Order;

public interface InvoicePdfService {

	File generateInvoicePdf(Order order) throws Exception;
	void sendOrderEmailWithPdf(Order order, File pdfInvoice) throws Exception;
	
}
