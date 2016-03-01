
/**
 * TrackingCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.apache.ws.axis2;

    /**
     *  TrackingCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class TrackingCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public TrackingCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public TrackingCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for trackProducts method
            * override this method for handling normal response from trackProducts operation
            */
           public void receiveResulttrackProducts(
                    org.apache.ws.axis2.TrackingStub.TrackProductsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from trackProducts operation
           */
            public void receiveErrortrackProducts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewProductPrice method
            * override this method for handling normal response from viewProductPrice operation
            */
           public void receiveResultviewProductPrice(
                    org.apache.ws.axis2.TrackingStub.ViewProductPriceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewProductPrice operation
           */
            public void receiveErrorviewProductPrice(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewProductCategory method
            * override this method for handling normal response from viewProductCategory operation
            */
           public void receiveResultviewProductCategory(
                    org.apache.ws.axis2.TrackingStub.ViewProductCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewProductCategory operation
           */
            public void receiveErrorviewProductCategory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewProductName method
            * override this method for handling normal response from viewProductName operation
            */
           public void receiveResultviewProductName(
                    org.apache.ws.axis2.TrackingStub.ViewProductNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewProductName operation
           */
            public void receiveErrorviewProductName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewProductBrand method
            * override this method for handling normal response from viewProductBrand operation
            */
           public void receiveResultviewProductBrand(
                    org.apache.ws.axis2.TrackingStub.ViewProductBrandResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewProductBrand operation
           */
            public void receiveErrorviewProductBrand(java.lang.Exception e) {
            }
                


    }
    