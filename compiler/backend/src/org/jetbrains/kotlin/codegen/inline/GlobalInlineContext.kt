/*
 * Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.inline

import java.util.*

class GlobalInlineContext {

    val state = LinkedList<MutableSet<String>>()

    fun enter() {
        state.push(hashSetOf())
    }

    fun exit() {
        val pop = state.pop()
        state.peek()?.addAll(pop)
    }

    fun recordTypeFromInlineFunction(type: String) = state.peek().add(type)

    fun isTypeFromInlineFunction(type: String) = state.peek().contains(type)
}