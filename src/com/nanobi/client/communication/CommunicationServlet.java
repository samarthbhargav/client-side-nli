/**
 * 
 */
package com.nanobi.client.communication;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.map.ObjectMapper;

import com.nanobi.client.constants.Params;
/**
 * @author Samarth
 *
 */
public class CommunicationServlet {
    private static final String URL = "http://localhost:8080/nli/Serv1";
    
    public TranslationResult getTranslation(String query) {
        TranslationResult result = null;

        try {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(URL);

            //Add any parameter if u want to send it with Post req.
            method.addParameter(Params.NLI_PARAM_QUERY, query);
            method.addParameter(Params.NLI_PARAM_AUTHTOKEN, Params.NLI_PARAM_AUTHTOKEN_VALUE);
            method.addParameter( Params.NLI_PARAM_LAST_TRANSLATION , "");
            method.addParameter(Params.NLI_PARAM_PRUNE, Params.NLI_PARAM_PRUNE_VALUE);
            
            
            int statusCode = client.executeMethod(method);

            if (statusCode != -1) {
                
                ObjectMapper mapper = new ObjectMapper();
                
                result = mapper.readValue( method.getResponseBodyAsString(), TranslationResult.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
