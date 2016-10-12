/*
 * Copyright 2016 Basit Parkar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 *  @date 6/10/16 11:12 AM
 *  @modified 6/10/16 11:12 AM
 */

package me.iz.mobility.loginextassignment.dagger.components;


import javax.inject.Singleton;

import dagger.Component;
import me.iz.mobility.loginextassignment.LogiNextApp;
import me.iz.mobility.loginextassignment.dagger.modules.AppModule;
import me.iz.mobility.loginextassignment.ui.activities.OrderDetailsActivity;
import me.iz.mobility.loginextassignment.ui.activities.OrdersActivity;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(LogiNextApp app);
    void inject(OrdersActivity activity);
    void inject(OrderDetailsActivity activity);
}