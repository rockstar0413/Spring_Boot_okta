/*
 * Copyright 2014 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.okta.spring.boot.sdk.cache

import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager

import org.testng.annotations.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.notNullValue

/**
 * @since 0.3.0
 */
class SpringCacheManagerTest {

    @Test(expectedExceptions = IllegalArgumentException)
    void testNullCacheManager() {
        new SpringCacheManager(null)
    }

    @Test
    void testGetCache() {

        def cacheName = 'name'
        def springCacheManager = new SimpleCacheManager();
        def springCache = new ConcurrentMapCache(cacheName)
        springCacheManager.setCaches([springCache])
        springCacheManager.afterPropertiesSet()

        SpringCacheManager cacheManager = new SpringCacheManager(springCacheManager)
        def cache = cacheManager.getCache(cacheName)
        assertThat cache, notNullValue()
        assertThat springCache, is(cache.springCache)
    }
}