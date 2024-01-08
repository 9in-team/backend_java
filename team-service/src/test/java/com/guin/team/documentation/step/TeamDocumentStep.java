package com.guin.team.documentation.step;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static com.guin.team.documentation.DocumentPreprocessor.requestPreprocessor;
import static com.guin.team.documentation.DocumentPreprocessor.responsePreprocessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

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
                fieldWithPath("teamTemplates").type(JsonFieldType.ARRAY).description("질문 템플렛"),
                fieldWithPath("teamTemplates[].type").type(JsonFieldType.STRING).description("질문 양식"),
                fieldWithPath("teamTemplates[].question").type(JsonFieldType.STRING).description("질문 내용"),
                fieldWithPath("teamTemplates[].option").type(JsonFieldType.STRING).description("양식이 체크박스 일시 항목"),
                fieldWithPath("hashTags").type(JsonFieldType.ARRAY).description("해시태그"),
                fieldWithPath("roles").type(JsonFieldType.ARRAY).description("포지션별 참여 인원"),
                fieldWithPath("roles[].name").type(JsonFieldType.STRING).description("포지션 이름"),
                fieldWithPath("roles[].requiredCount").type(JsonFieldType.NUMBER).description("필요 인원 수")
        );
    }

    private static ResponseFieldsSnippet response() {
        return responseFields(
                fieldWithPath("teamId").type(JsonFieldType.NUMBER).description("모집글 아이디"),
                fieldWithPath("openChatUrl").type(JsonFieldType.STRING).description("오픈채팅방 URL"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("모집글 내용"),
                fieldWithPath("subject").type(JsonFieldType.STRING).description("모집글 제목"),
                fieldWithPath("subjectType").type(JsonFieldType.STRING).description("프로젝트 종류"),
                fieldWithPath("teamTemplates").type(JsonFieldType.ARRAY).description("질문 템플렛"),
                fieldWithPath("teamTemplates[].type").type(JsonFieldType.STRING).description("질문 양식"),
                fieldWithPath("teamTemplates[].question").type(JsonFieldType.STRING).description("질문 내용"),
                fieldWithPath("teamTemplates[].option").type(JsonFieldType.STRING).description("양식이 체크박스 일시 항목"),
                fieldWithPath("hashTags").type(JsonFieldType.ARRAY).description("해시태그"),
                fieldWithPath("teamRoleDetail").type(JsonFieldType.ARRAY).description("포지션별 참여 인원"),
                fieldWithPath("teamRoleDetail[].name").type(JsonFieldType.STRING).description("포지션 이름"),
                fieldWithPath("teamRoleDetail[].requiredCount").type(JsonFieldType.NUMBER).description("필요 인원 수"),
                fieldWithPath("teamRoleDetail[].hiredCount").type(JsonFieldType.NUMBER).description("참여한 인원 수")
        );
    }

}
