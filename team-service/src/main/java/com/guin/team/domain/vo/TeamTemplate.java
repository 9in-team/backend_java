package com.guin.team.domain.vo;

import com.guin.team.domain.constant.TemplateType;

import java.util.List;

public record TeamTemplate(
        Long id,
        TemplateType templateType,
        String question,
        List<CheckboxTemplate> checkBoxTemplates
) {

    public record CheckboxTemplate(
            Long id,
            String optionName
    ) {
    }

}
