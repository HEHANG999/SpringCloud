
package com.project.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MessageServiceImplService", targetNamespace = "http://impl.service.project.com/", wsdlLocation = "http://192.168.43.252:8888/message?wsdl")
public class MessageServiceImplService
    extends Service
{

    private final static URL MESSAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException MESSAGESERVICEIMPLSERVICE_EXCEPTION;
    private final static QName MESSAGESERVICEIMPLSERVICE_QNAME = new QName("http://impl.service.project.com/", "MessageServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.43.252:8888/message?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MESSAGESERVICEIMPLSERVICE_WSDL_LOCATION = url;
        MESSAGESERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public MessageServiceImplService() {
        super(__getWsdlLocation(), MESSAGESERVICEIMPLSERVICE_QNAME);
    }

    public MessageServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MESSAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public MessageServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, MESSAGESERVICEIMPLSERVICE_QNAME);
    }

    public MessageServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MESSAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public MessageServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MessageServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MessageServiceImpl
     */
    @WebEndpoint(name = "MessageServiceImplPort")
    public MessageServiceImpl getMessageServiceImplPort() {
        return super.getPort(new QName("http://impl.service.project.com/", "MessageServiceImplPort"), MessageServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MessageServiceImpl
     */
    @WebEndpoint(name = "MessageServiceImplPort")
    public MessageServiceImpl getMessageServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.service.project.com/", "MessageServiceImplPort"), MessageServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MESSAGESERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw MESSAGESERVICEIMPLSERVICE_EXCEPTION;
        }
        return MESSAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
