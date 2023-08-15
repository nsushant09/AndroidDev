package com.neupanesushant.learntesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ResourceComparerTest {

    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun initializeResourceComparer() {
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertThat(
            resourceComparer.isEqual(
                context,
                R.string.this_is_a_test_string,
                "This is a test string"
            )
        ).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertThat(
            resourceComparer.isEqual(
                context,
                R.string.this_is_a_test_string,
                "This is a not a test string"
            )
        ).isFalse()
    }
}