package org.apache.olingo.odata2.jpa.processor.ref.util;

import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomODataJPAProcessor extends ODataJPAProcessorCaP {

  public static final Logger LOG = Logger.getLogger(CustomODataJPAProcessor.class.getName());
  /**
   * Constructor
   *
   * @param oDataJPAContext non null OData JPA Context object
   */
  public CustomODataJPAProcessor(ODataJPAContext oDataJPAContext) {
    super(oDataJPAContext);
  }

  @Override
  public ODataResponse readEntity(GetEntityUriInfo uriParserResultView, String contentType) throws ODataException {
    LOG.log(Level.INFO, "Read entity..." + uriParserResultView.getTargetEntitySet().getName());
    return super.readEntity(uriParserResultView, contentType);
  }

  @Override
  public ODataResponse readEntitySet(final GetEntitySetUriInfo uriParserResultView, final String contentType)
      throws ODataException {
    LOG.log(Level.INFO, "Read entity set..." + uriParserResultView.getTargetEntitySet().getName());
    List<Object> jpaEntities = jpaProcessor.process(uriParserResultView);
    ODataResponse oDataResponse =
        responseBuilder.build(uriParserResultView, jpaEntities, contentType);
    return oDataResponse;
  }
}