/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Optional {
    private static final Optional EMPTY = new Optional();

    private final Verdict value;

    private Optional() {
        this.value = null;
    }

    public static Optional empty() {
        @SuppressWarnings("unchecked")
        Optional t = EMPTY;
        return t;
    }

    private Optional(Verdict value) {
        this.value = Objects.requireNonNull(value);
    }

    public static Optional of(Verdict value) {
        return new Optional(value);
    }

    public static Optional ofNullable(Verdict value) {
        return value == null ? empty() : of(value);
    }

    public Verdict get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }
}
