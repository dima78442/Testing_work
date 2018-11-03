/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.dima.testing_work.data.db;

import com.dima.testing_work.data.db.model.ItemSaved;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by janisharali on 08/12/16.
 */

public interface DbHelper {

    Observable<Long> insertItem(final ItemSaved item);

    Observable<List<ItemSaved>> getAllItems();

    Observable<Void> deleteItem(ItemSaved item);

    Observable<Void> deleteItemById(long id);

    Observable<Boolean> isSavedItemsEmpty();

    Observable<Long> getItemKey(final ItemSaved item);

   /* Observable<List<Question>> getAllQuestions();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

    Observable<Boolean> saveOptionList(List<Option> optionList);*/
}
