/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.composed.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An ExcludeFromScan annotation is used on Spring's {@link org.springframework.context.annotation.Configuration
 * Configuration} classes to exclude those from Springs component scan mechanism.
 * <p/>
 * Some application that defines the {@link org.springframework.context.annotation.ComponentScan ComponentScan}
 * annotation may define a filter that excludes all types annotated with {@link ExcludeFromScan}.
 *
 * Example:
 * <pre>
 *     @ComponentScan(excludeFilters = @ComponentScan.Filter(ExcludeFromScan.class))
 * </pre>
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcludeFromScan {
}
