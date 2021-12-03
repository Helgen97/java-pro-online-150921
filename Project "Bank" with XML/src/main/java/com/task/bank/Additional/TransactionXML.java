package com.task.bank.Additional;

import com.task.bank.Entities.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class TransactionXML {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;

    public TransactionXML() {
        try {
            builder = factory.newDocumentBuilder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createXML(List<Transaction> transactionList, String username, String path) throws TransformerException {
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("transactions");
        doc.appendChild(rootElement);

        for (Transaction transaction : transactionList) {
            rootElement.appendChild(getTransaction(doc, transaction));
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File(path + "TransactionsFiles/" + username + "Transactions.xml"));

        transformer.transform(source, result);
    }

    private static Node getTransaction(Document doc, Transaction transaction) {
        Element tr = doc.createElement("transaction");
        tr.setAttribute("id", String.valueOf(transaction.getId()));
        tr.appendChild(getTransactionElement(doc, "client", transaction.getClient().getNickname()));
        tr.appendChild(getTransactionElement(doc, "currency", transaction.getCurrencyName()));
        tr.appendChild(getTransactionElement(doc, "value", String.valueOf(transaction.getValue())));
        tr.appendChild(getTransactionElement(doc, "receiver", transaction.getReceiverName()));
        tr.appendChild(getTransactionElement(doc, "currencyReceive", transaction.getCurrencyName()));
        tr.appendChild(getTransactionElement(doc, "comment", transaction.getComment()));

        return tr;
    }

    private static Node getTransactionElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
