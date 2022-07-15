package com.amazon.docstore.validator;

import com.amazon.docstore.exception.GenericBadRequestException;
import com.amazon.docstore.models.Article;
import com.amazon.docstore.models.SearchArticle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Article.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Inside article validator {} {}",target, errors);
        Article article = (Article) target;
        if (ObjectUtils.isEmpty(article)) {
            throw new GenericBadRequestException("Provide search details");
        } else if (ObjectUtils.isEmpty(article.getTags())) {
            throw new GenericBadRequestException("Article must have tags");
        } else if (StringUtils.isEmpty(article.getTitle()) || article.getTitle().length() < 5) {
            throw new GenericBadRequestException("Article must have title of size more than 5");
        } else if (StringUtils.isEmpty(article.getContent()) || article.getContent().length() < 5) {
            throw new GenericBadRequestException("Article must have content of size more than 5");
        }
        log.debug("Article validation success");
    }
}
