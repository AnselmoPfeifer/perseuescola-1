package com.htcursos.webservice.f2b;

public class WSBillingStatusTest {

	public static void main(String args[]) {
		try {
			// Create request endpoint
			java.security.Security
					.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			java.lang.System.setProperty("java.protocol.handler.pkgs",
					"com.sun.net.ssl.internal.www.protocol");
			java.net.URL endpoint = new java.net.URL(
					"https://www.f2b.com.br/WSBillingStatus");
			// Create request message
			javax.xml.soap.MessageFactory mf = javax.xml.soap.MessageFactory
					.newInstance();
			javax.xml.soap.SOAPMessage request = mf.createMessage();
			// Get message elements
			javax.xml.soap.SOAPPart part = request.getSOAPPart();
			javax.xml.soap.SOAPEnvelope envelope = part.getEnvelope();
			// Remove header
			javax.xml.soap.SOAPHeader header = envelope.getHeader();
			((javax.xml.soap.Node) header).detachNode();
			// Add content to body
			javax.xml.soap.SOAPBody body = envelope.getBody();
			javax.xml.soap.Name F2bSituacaoCobrancaName = envelope.createName(
					"F2bSituacaoCobranca", "m",
					"http://www.f2b.com.br/soap/wsbillingstatus.xsd");
			javax.xml.soap.SOAPBodyElement F2bSituacaoCobranca = body
					.addBodyElement(F2bSituacaoCobrancaName);
			// Add mensagem
			javax.xml.soap.SOAPElement mensagem = F2bSituacaoCobranca
					.addChildElement("mensagem");
			mensagem.addAttribute(envelope.createName("data"),
					(new java.text.SimpleDateFormat("yyyy-MM-dd"))
							.format(new java.util.Date()));
			mensagem.addAttribute(envelope.createName("numero"),
					(new java.text.SimpleDateFormat("HHmmss"))
							.format(new java.util.Date()));

			// Add cliente
			javax.xml.soap.SOAPElement cliente = F2bSituacaoCobranca
					.addChildElement("cliente");
			cliente.addAttribute(envelope.createName("conta"),
					"9023010001230123");
			cliente.addAttribute(envelope.createName("senha"), "senha123");

			// Add cobranca
			javax.xml.soap.SOAPElement cobranca = F2bSituacaoCobranca
					.addChildElement("cobranca");

			cobranca.addAttribute(envelope.createName("situacao"), "0");// 0 =
																		// todas;
																		// 1=somente
																		// registradas;2=somente
																		// pagas;3
																		// =
																		// somente
																		// vencidas

			// Enviar p/ o nosso servidor
			// ********************** Intervalos de cobran�as
			// ************************************
			cobranca.addAttribute(envelope.createName("numero"), "85423");
			// e/ou
			cobranca.addAttribute(envelope.createName("numero_final"), "85425");
			// ***********************************************************************************

			// ou ---------------

			// ********************** Intervalo (Data de registro)
			// *******************************
			// cobranca.addAttribute(envelope.createName("registro"),
			// "2004-10-07");
			// e/ou
			// cobranca.addAttribute(envelope.createName("registro_final"),
			// "2004-10-27");
			// ***********************************************************************************

			// ou ---------------

			// ********************** Intervalo (Data de vencimento)
			// *****************************
			// cobranca.addAttribute(envelope.createName("vencimento"),
			// "2004-10-30");
			// e/ou
			// cobranca.addAttribute(envelope.createName("vencimento_final"),
			// "2004-11-30");
			// ***********************************************************************************

			// ou ---------------

			// ********************** Intervalo (Data de processamento)
			// **************************
			// cobranca.addAttribute(envelope.createName("processamento"),
			// "2004-10-30");
			// e/ou
			// cobranca.addAttribute(envelope.createName("processamento_final"),
			// "2004-11-30");
			// ***********************************************************************************

			// ou ---------------

			// ********************** Intervalo (Data de cr�dito)
			// ********************************
			// cobranca.addAttribute(envelope.createName("credito"),
			// "2004-10-30");
			// e/ou
			// cobranca.addAttribute(envelope.createName("credito_final"),
			// "2004-11-30");
			// ***********************************************************************************

			// e/ou ---------------
			// cobranca.addAttribute(envelope.createName("cod_sacado"),
			// "XYZ1234");
			// e/ou ---------------
			// cobranca.addAttribute(envelope.createName("cod_grupo"),
			// "GrupoTeste");
			// e/ou ---------------
			// cobranca.addAttribute(envelope.createName("tipo_pagamento"),
			// "B");
			// "B" - Boleto; "C" - Cart�o de Cr�dito, "D" - Cart�o de D�bito,
			// "F" - Entre contas F2b,
			// "M" - Registrado pela F2b, "S" - Registrado pelo sacador, "T" -
			// Transfer�ncia on-line.
			// e/ou ---------------
			// cobranca.addAttribute(envelope.createName("numero_pagamento"),
			// "123456");

			System.out.println("request: " + request);
			request.writeTo(System.out);
			System.out.println("");

			// Send request
			javax.xml.soap.SOAPConnectionFactory scf = javax.xml.soap.SOAPConnectionFactory
					.newInstance();
			javax.xml.soap.SOAPConnection sc = scf.createConnection();
			javax.xml.soap.SOAPMessage response = sc.call(request, endpoint);
			System.out.println("response: " + response);
			response.writeTo(System.out);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
