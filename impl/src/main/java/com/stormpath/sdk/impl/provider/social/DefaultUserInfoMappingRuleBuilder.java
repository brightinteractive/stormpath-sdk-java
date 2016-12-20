/*
* Copyright 2016 Stormpath, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.stormpath.sdk.impl.provider.social;

import com.stormpath.sdk.lang.Assert;
import com.stormpath.sdk.provider.social.UserInfoMappingRule;
import com.stormpath.sdk.provider.social.UserInfoMappingRuleBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @since 1.3.0
 */
public class DefaultUserInfoMappingRuleBuilder implements UserInfoMappingRuleBuilder {

    private String name;
    private Set<String> accountAttributes;

    public UserInfoMappingRuleBuilder setName(String name){
        Assert.hasText(name, "name argument cannot be null or empty.");
        this.name = name;
        return this;
    }

    public UserInfoMappingRuleBuilder setAccountAttributes(String... accountAttributes){
        Assert.notEmpty(accountAttributes, "accountAttributes cannot be null or empty.");

        Set<String> names = new LinkedHashSet<String>(accountAttributes.length);
        for (String attrName : accountAttributes) {
            Assert.hasText("individual accountAttributes cannot be null or empty.");
            names.add(attrName);
        }
        this.accountAttributes = names;
        return this;
    }

    public UserInfoMappingRuleBuilder setAccountAttributes(Set<String> accountAttributes){
        Assert.notEmpty(accountAttributes, "accountAttributes cannot be null or empty.");

        this.accountAttributes = accountAttributes;
        return this;
    }

    public UserInfoMappingRule build(){
        Assert.hasText(name, "name argument cannot be null or empty.");
        Assert.notEmpty(accountAttributes, "accountAttributes cannot be null or empty.");

        return new DefaultUserInfoMappingRule(name, accountAttributes);
    }
}
