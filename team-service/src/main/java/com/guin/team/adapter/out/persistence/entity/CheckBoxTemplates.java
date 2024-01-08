package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.domain.constant.TemplateType;
import com.guin.team.global.error.InvalidCheckBoxTemplateException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckBoxTemplates {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "checkbox_template_id")
    private List<CheckBoxTemplateEntity> checkBoxTemplates;

    private CheckBoxTemplates(List<CheckBoxTemplateEntity> checkBoxTemplates) {
        this.checkBoxTemplates = checkBoxTemplates;
    }

    public static CheckBoxTemplates of(final List<CheckBoxTemplateEntity> checkBoxTemplates,
                                       final TemplateType templateType) {
        validate(checkBoxTemplates, templateType);
        return new CheckBoxTemplates(checkBoxTemplates);
    }

    private static void validate(final List<CheckBoxTemplateEntity> checkBoxTemplates,
                                 final TemplateType templateType) {
        if(templateType == TemplateType.CHECKBOX) {
            if(checkBoxTemplates == null) {
                throw new InvalidCheckBoxTemplateException();
            }
        }
    }
}
