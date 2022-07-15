package com.amazon.docstore.validator;

import com.amazon.docstore.exception.GenericBadRequestException;
import com.amazon.docstore.models.SearchArticle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class SearchArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SearchArticle.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Inside search validator {} {}",target, errors);
        SearchArticle searchArticle = (SearchArticle) target;
        if (ObjectUtils.isEmpty(searchArticle)) {
            throw new GenericBadRequestException("Provide search details");
        } /** else if (ObjectUtils.isEmpty(searchArticle.getTags())) {  // This code was added to make tags mandatory. Need to discuss with PM
            throw new GenericBadRequestException("Tags are mandatory parameters");
        } */
        else if (!StringUtils.isEmpty(searchArticle.getQuery()) && searchArticle.getQuery().length() < 3) {
            throw new GenericBadRequestException("Search query size must be greater than 3");
        }
        log.debug("Search validation success");
    }
}
