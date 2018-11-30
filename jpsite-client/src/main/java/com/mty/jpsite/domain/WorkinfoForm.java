package com.mty.jpsite.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by jiangpeng on 2018/10/24.
 */
public class WorkinfoForm {
    public interface Update{}
    public interface Add{}

    @NotNull(groups = {Update.class})
    @Null(groups = {Add.class})
    Long id;
}
