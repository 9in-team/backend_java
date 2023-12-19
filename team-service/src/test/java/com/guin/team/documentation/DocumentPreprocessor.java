package com.guin.team.documentation;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

public class DocumentPreprocessor {

    private static final String HTTP = "http";
    private static final String LOCALHOST = "localhost";

    public static OperationRequestPreprocessor requestPreprocessor() {
        return Preprocessors.preprocessRequest(
                Preprocessors.modifyUris()
                        .scheme(HTTP)
                        .host(LOCALHOST)
                        .removePort(),
                Preprocessors.prettyPrint());
    }

    public static OperationResponsePreprocessor responsePreprocessor() {
        return Preprocessors.preprocessResponse(Preprocessors.prettyPrint());
    }

}
