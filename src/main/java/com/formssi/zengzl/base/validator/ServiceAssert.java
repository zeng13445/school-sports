package com.formssi.zengzl.base.validator;

import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.exception.ValidateException;
import java.util.Collection;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author: zilong
 * @description: 服务断言，用于逻辑判断，抛出服务异常
 * @created: 2020-07-21
 */
public class ServiceAssert {

    /**
     * Assert a boolean expression, throwing an {@code IllegalArgumentException}
     * if the expression evaluates to {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @param code       error code
     * @param message    the exception message to use if the assertion fails
     * @throws ValidateException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, ResultCode code, String message) {
        if (!expression) {
            throw new ValidateException(code, message);
        }
    }
    public static void isTrue(boolean expression, ResultCode code) {
        if (!expression) {
            throw new ValidateException(code);
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param code    erro code
     * @param message the exception message to use if the assertion fails
     * @throws ValidateException if the object is {@code null}
     */
    public static void notNull(Object object, ResultCode code, String message) {
        if (object == null || object == "null") {
            throw new ValidateException(code, message);
        }
    }
    public static void notNull(Object object, ResultCode code) {
        if (object == null || object == "null") {
            throw new ValidateException(code);
        }
    }

    /**
     * Assert that an object is {@code null}.
     *  @param object  the object to check
     * @param code    erro code
     * @param message the exception message to use if the assertion fails
     */
    public static void isNull(Object object, ResultCode code, String message) {
        if (object != null) {
            throw new ValidateException(code, message);
        }
    }

    public static void isNull(Object object, ResultCode code) {
        if (object != null) {
            throw new ValidateException(code);
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array   the array to check
     * @param code    error code
     * @param message the exception message to use if the assertion fails
     * @throws ValidateException if the object array is {@code null} or contains no elements
     */
    public static void notNullByArray(Object[] array, Integer code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new ValidateException(code, message);
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @param code       error code
     * @param message    the exception message to use if the assertion fails
     * @throws ValidateException if the collection is {@code null} or
     *                          contains no elements
     */
    public static void notNullByCollection(Collection<?> collection, Integer code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ValidateException(code, message);
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map     the map to check
     * @param code    error code
     * @param message the exception message to use if the assertion fails
     * @throws ValidateException if the map is {@code null} or contains no entries
     */
    public static void notNullByMap(Map<?, ?> map, Integer code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ValidateException(code, message);
        }
    }
}
