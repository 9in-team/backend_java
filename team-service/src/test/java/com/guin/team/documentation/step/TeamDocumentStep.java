package com.guin.team.documentation.step;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static com.guin.team.documentation.DocumentPreprocessor.requestPreprocessor;
import static com.guin.team.documentation.DocumentPreprocessor.responsePreprocessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class TeamDocumentStep {

    public static RestDocumentationResultHandler resultHandler() {
        return document(
                "createProject",
                requestPreprocessor(),
                responsePreprocessor(),
                request(),
                response()
        );
    }

    private static RequestFieldsSnippet request() {
        return requestFields(
                fieldWithPath("subject").type(JsonFieldType.STRING).description("모집글 제목"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("모집글 내용"),
                fieldWithPath("openChatUrl").type(JsonFieldType.STRING).description("오픈채팅방 URL"),
                fieldWithPath("subjectType").type(JsonFieldType.STRING).description("프로젝트 종류"),
                fieldWithPath("teamTemplates").type(JsonFieldType.ARRAY).description("질문 템플렛").optional(),
                fieldWithPath("types").type(JsonFieldType.ARRAY).description("해시태그").optional(),
                fieldWithPath("roles").type(JsonFieldType.ARRAY).description("포지션별 참여 인원").optional()
        );
    }

    private static ResponseFieldsSnippet response() {
        return responseFields(
                fieldWithPath("teamId").type(JsonFieldType.NUMBER).description("모집글 아이디"),
                fieldWithPath("openChatUrl").type(JsonFieldType.STRING).description("오픈채팅방 URL"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("모집글 내용"),
                fieldWithPath("subject").type(JsonFieldType.STRING).description("모집글 제목"),
                fieldWithPath("subjectType").type(JsonFieldType.STRING).description("프로젝트 종류"),
                fieldWithPath("teamTemplates").type(JsonFieldType.ARRAY).description("질문 템플렛").optional(),
                fieldWithPath("types").type(JsonFieldType.ARRAY).description("해시태그").optional(),
                fieldWithPath("teamRoleDetail").type(JsonFieldType.ARRAY).description("포지션별 참여 인원").optional()
        );
    }

}
