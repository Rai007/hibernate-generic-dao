/* Copyright 2013 David Wolverton
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
package net.da.backing.data.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A convenient fully-featured implementation of ISearch and IMutableSearch for
 * general use in Java code.
 * 
 * @author dwolverton
 */
public class Search implements IMutableSearch, Serializable {

    private static final long serialVersionUID = 1L;

    protected int firstResult = -1; // -1 stands for unspecified

    protected int maxResults = -1;  // -1 stands for unspecified

    protected int page = -1;        // -1 stands for unspecified

    protected Class<?> searchClass;

    protected List<Filter> filters = new ArrayList<>();

    protected boolean disjunction;

    protected List<Sort> sorts = new ArrayList<>();

    protected List<Field> fields = new ArrayList<>();

    protected boolean distinct;

    protected List<String> fetches = new ArrayList<>();

    protected int resultMode = RESULT_AUTO;

    public Search() {

    }

    public Search(Class<?> searchClass) {
            this.searchClass = searchClass;
    }

    @Override
    public Search setSearchClass(Class<?> searchClass) {
            this.searchClass = searchClass;
            return this;
    }

    @Override
    public Class<?> getSearchClass() {
            return searchClass;
    }

    // Filters
    public Search addFilter(Filter filter) {
            SearchUtil.addFilter(this, filter);
            return this;
    }

    public Search addFilters(Filter... filters) {
            SearchUtil.addFilters(this, filters);
            return this;
    }

    /**
     * Add a filter that uses the == operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterEqual(String property, Object value) {
            SearchUtil.addFilterEqual(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the >= operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterGreaterOrEqual(String property, Object value) {
            SearchUtil.addFilterGreaterOrEqual(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the > operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterGreaterThan(String property, Object value) {
            SearchUtil.addFilterGreaterThan(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the IN operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterIn(String property, Collection<?> value) {
            SearchUtil.addFilterIn(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the IN operator.
     * 
     * <p>
     * This takes a variable number of parameters. Any number of values can be
     * specified.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterIn(String property, Object... value) {
            SearchUtil.addFilterIn(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the NOT IN operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterNotIn(String property, Collection<?> value) {
            SearchUtil.addFilterNotIn(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the NOT IN operator.
     * 
     * <p>
     * This takes a variable number of parameters. Any number of values can be
     * specified.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterNotIn(String property, Object... value) {
            SearchUtil.addFilterNotIn(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the <= operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterLessOrEqual(String property, Object value) {
            SearchUtil.addFilterLessOrEqual(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the < operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterLessThan(String property, Object value) {
            SearchUtil.addFilterLessThan(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the LIKE operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterLike(String property, String value) {
            SearchUtil.addFilterLike(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the ILIKE operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterILike(String property, String value) {
            SearchUtil.addFilterILike(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the != operator.
     * 
     * @param property
     * @param value
     * @return 
     */
    public Search addFilterNotEqual(String property, Object value) {
            SearchUtil.addFilterNotEqual(this, property, value);
            return this;
    }

    /**
     * Add a filter that uses the IS NULL operator.
     * 
     * @param property
     * @return 
     */
    public Search addFilterNull(String property) {
            SearchUtil.addFilterNull(this, property);
            return this;
    }

    /**
     * Add a filter that uses the IS NOT NULL operator.
     * 
     * @param property
     * @return 
     */
    public Search addFilterNotNull(String property) {
            SearchUtil.addFilterNotNull(this, property);
            return this;
    }

    /**
     * Add a filter that uses the IS EMPTY operator.
     * 
     * @param property
     * @return 
     */
    public Search addFilterEmpty(String property) {
            SearchUtil.addFilterEmpty(this, property);
            return this;
    }

    /**
     * Add a filter that uses the IS NOT EMPTY operator.
     * 
     * @param property
     * @return 
     */
    public Search addFilterNotEmpty(String property) {
            SearchUtil.addFilterNotEmpty(this, property);
            return this;
    }

    /**
     * Add a filter that uses the AND operator.
     * 
     * <p>
     * This takes a variable number of parameters. Any number of <code>Filter
     * </code>s can be
     * specified.
     * 
     * @param filters
     * @return 
     */
    public Search addFilterAnd(Filter... filters) {
            SearchUtil.addFilterAnd(this, filters);
            return this;
    }

    /**
     * Add a filter that uses the OR operator.
     * 
     * <p>
     * This takes a variable number of parameters. Any number of <code>Filter
     * </code>s can be
     * specified.
     * 
     * @param filters
     * @return 
     */
    public Search addFilterOr(Filter... filters) {
            SearchUtil.addFilterOr(this, filters);
            return this;
    }

    /**
     * Add a filter that uses the NOT operator.
     * 
     * @param filter
     * @return 
     */
    public Search addFilterNot(Filter filter) {
            SearchUtil.addFilterNot(this, filter);
            return this;
    }

    /**
     * Add a filter that uses the SOME operator.
     * 
     * @param property
     * @param filter
     * @return 
     */
    public Search addFilterSome(String property, Filter filter) {
            SearchUtil.addFilterSome(this, property, filter);
            return this;
    }

    /**
     * Add a filter that uses the ALL operator.
     * 
     * @param property
     * @param filter
     * @return 
     */
    public Search addFilterAll(String property, Filter filter) {
            SearchUtil.addFilterAll(this, property, filter);
            return this;
    }

    /**
     * Add a filter that uses the NONE operator.
     * 
     * @param property
     * @param filter
     * @return 
     */
    public Search addFilterNone(String property, Filter filter) {
            SearchUtil.addFilterNone(this, property, filter);
            return this;
    }

    /**
     * Add a filter that uses a custom expression.
     * 
     * @param expression
     * @return 
     * @see {@link Filter#custom(String)}
     */
    public Search addFilterCustom(String expression) {
            SearchUtil.addFilterCustom(this, expression);
            return this;
    }

    /**
     * Add a filter that uses a custom expression.
     * 
     * @param expression
     * @param values
     * @return 
     * @see {@link Filter#custom(String, Object...)}
     */
    public Search addFilterCustom(String expression, Object... values) {
            SearchUtil.addFilterCustom(this, expression, values);
            return this;
    }

    /**
     * Add a filter that uses a custom expression.
     * 
     * @param expression
     * @param values
     * @return 
     * @see {@link Filter#custom(String, Collection)}
     */
    public Search addFilterCustom(String expression, Collection<?> values) {
            SearchUtil.addFilterCustom(this, expression, values);
            return this;
    }

    public void removeFilter(Filter filter) {
            SearchUtil.removeFilter(this, filter);
    }

    /**
     * Remove all filters on the given property.
     * 
     * @param property
     */
    public void removeFiltersOnProperty(String property) {
            SearchUtil.removeFiltersOnProperty(this, property);
    }

    public void clearFilters() {
            SearchUtil.clearFilters(this);
    }

    public boolean isDisjunction() {
            return disjunction;
    }

    /**
     * Filters added to a search are "ANDed" together if this is false (default)
     * and "ORed" if it is set to true.
     * 
     * @param disjunction
     * @return 
     */
    @Override
    public Search setDisjunction(boolean disjunction) {
            this.disjunction = disjunction;
            return this;
    }

    // Sorts
    public Search addSort(Sort sort) {
            SearchUtil.addSort(this, sort);
            return this;
    }

    public Search addSorts(Sort... sorts) {
            SearchUtil.addSorts(this, sorts);
            return this;
    }

    /**
     * Add ascending sort by property
     * 
     * @param property
     * @return 
     */
    public Search addSortAsc(String property) {
            SearchUtil.addSortAsc(this, property);
            return this;
    }

    /**
     * Add ascending sort by property
     * 
     * @param property
     * @param ignoreCase
     * @return 
     */
    public Search addSortAsc(String property, boolean ignoreCase) {
            SearchUtil.addSortAsc(this, property, ignoreCase);
            return this;
    }

    /**
     * Add descending sort by property
     * 
     * @param property
     * @return 
     */
    public Search addSortDesc(String property) {
            SearchUtil.addSortDesc(this, property);
            return this;
    }

    /**
     * Add descending sort by property
     * 
     * @param property
     * @param ignoreCase
     * @return 
     */
    public Search addSortDesc(String property, boolean ignoreCase) {
            SearchUtil.addSortDesc(this, property, ignoreCase);
            return this;
    }

    /**
     * Add sort by property. Ascending if <code>desc == false</code>, descending
     * if <code>desc == true</code>.
     * 
     * @param property
     * @param desc
     * @return 
     */
    public Search addSort(String property, boolean desc) {
            SearchUtil.addSort(this, property, desc);
            return this;
    }

    /**
     * Add sort by property. Ascending if <code>desc == false</code>, descending
     * if <code>desc == true</code>.
     * 
     * @param property
     * @param desc
     * @param ignoreCase
     * @return 
     */
    public Search addSort(String property, boolean desc, boolean ignoreCase) {
            SearchUtil.addSort(this, property, desc, ignoreCase);
            return this;
    }

    public void removeSort(Sort sort) {
            SearchUtil.removeSort(this, sort);
    }

    public void removeSort(String property) {
            SearchUtil.removeSort(this, property);
    }

    public void clearSorts() {
            SearchUtil.clearSorts(this);
    }

    // Fields
    public Search addField(Field field) {
            SearchUtil.addField(this, field);
            return this;
    }

    public Search addFields(Field... fields) {
            SearchUtil.addFields(this, fields);
            return this;
    }

    /**
     * If this field is used with <code>resultMode == RESULT_MAP</code>, the
     * <code>property</code> will also be used as the key for this value in the
     * map.
     * 
     * @param property
     * @return 
     */
    public Search addField(String property) {
            SearchUtil.addField(this, property);
            return this;
    }

    /**
     * If this field is used with <code>resultMode == RESULT_MAP</code>, the
     * <code>key</code> will be used as the key for this value in the map.
     * 
     * @param property
     * @param key
     * @return 
     */
    public Search addField(String property, String key) {
            SearchUtil.addField(this, property, key);
            return this;
    }

    /**
     * If this field is used with <code>resultMode == RESULT_MAP</code>, the
     * <code>property</code> will also be used as the key for this value in the
     * map.
     * 
     * @param property
     * @param operator
     * @return 
     */
    public Search addField(String property, int operator) {
            SearchUtil.addField(this, property, operator);
            return this;
    }

    /**
     * If this field is used with <code>resultMode == RESULT_MAP</code>, the
     * <code>key</code> will be used as the key for this value in the map.
     * 
     * @param property
     * @param operator
     * @param key
     * @return 
     */
    public Search addField(String property, int operator, String key) {
            SearchUtil.addField(this, property, operator, key);
            return this;
    }

    public void removeField(Field field) {
            SearchUtil.removeField(this, field);
    }

    public void removeField(String property) {
            SearchUtil.removeField(this, property);
    }

    public void removeField(String property, String key) {
            SearchUtil.removeField(this, property, key);
    }

    public void clearFields() {
            SearchUtil.clearFields(this);
    }

    @Override
    public boolean isDistinct() {
            return distinct;
    }

    @Override
    public Search setDistinct(boolean distinct) {
            this.distinct = distinct;
            return this;
    }

    @Override
    public int getResultMode() {
            return resultMode;
    }

    @Override
    public Search setResultMode(int resultMode) {
            if (resultMode < 0 || resultMode > 4)
                    throw new IllegalArgumentException("Result Mode ( " + resultMode + " ) is not a valid option.");
            this.resultMode = resultMode;
            return this;
    }

    // Fetches
    public Search addFetch(String property) {
            SearchUtil.addFetch(this, property);
            return this;
    }

    public Search addFetches(String... properties) {
            SearchUtil.addFetches(this, properties);
            return this;
    }

    public void removeFetch(String property) {
            SearchUtil.removeFetch(this, property);
    }

    public void clearFetches() {
            SearchUtil.clearFetches(this);
    }

    public void clear() {
            SearchUtil.clear(this);
    }

    // Paging
    @Override
    public int getFirstResult() {
            return firstResult;
    }

    @Override
    public Search setFirstResult(int firstResult) {
            this.firstResult = firstResult;
            return this;
    }

    @Override
    public int getPage() {
            return page;
    }

    @Override
    public Search setPage(int page) {
            this.page = page;
            return this;
    }

    @Override
    public int getMaxResults() {
            return maxResults;
    }

    @Override
    public Search setMaxResults(int maxResults) {
            this.maxResults = maxResults;
            return this;
    }

    public void clearPaging() {
            SearchUtil.clearPaging(this);
    }

    /**
     * Create a copy of this search. All collections are copied into new
     * collections, but them items in those collections are not duplicated; they
     * still point to the same objects.
     * 
     * @return 
     */
    public Search copy() {
            Search dest = new Search();
            SearchUtil.copy(this, dest);
            return dest;
    }

    @Override
    public boolean equals(Object obj) {
            return SearchUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
            return SearchUtil.hashCode(this);
    }

    @Override
    public String toString() {
            return SearchUtil.toString(this);
    }

    @Override
    public List<Filter> getFilters() {
            return filters;
    }

    @Override
    public Search setFilters(List<Filter> filters) {
            this.filters = filters;
            return this;
    }

    @Override
    public List<Sort> getSorts() {
            return sorts;
    }

    @Override
    public Search setSorts(List<Sort> sorts) {
            this.sorts = sorts;
            return this;
    }

    @Override
    public List<Field> getFields() {
            return fields;
    }

    @Override
    public Search setFields(List<Field> fields) {
            this.fields = fields;
            return this;
    }

    @Override
    public List<String> getFetches() {
            return fetches;
    }

    @Override
    public Search setFetches(List<String> fetches) {
            this.fetches = fetches;
            return this;
    }

}
