package com.nanobi.client.service.model;

import java.util.HashMap;
import java.util.Map;


public class ServiceRequest
{
    Map<String, Object> params = new HashMap<String, Object>();


    public ServiceRequest( Map<String, String> params )
    {
        if ( params != null ) {
            for ( String key : params.keySet() ) {
                this.setParam( key, params.get( key ) );
            }
        }
    }


    public Object getParam( String param )
    {
        return params.get( param );
    }


    public void setParam( String param, Object value )
    {
        params.put( param, value );
    }
}
