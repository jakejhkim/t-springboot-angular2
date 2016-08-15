'use strict';

import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {routing, appRouterProviders} from "./app.routes";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";
import {PostComponent} from "./post/post.component";

@NgModule({
    declarations: [AppComponent, HomeComponent, PostComponent],
    imports: [BrowserModule, FormsModule, ReactiveFormsModule, routing],
    providers: [
        appRouterProviders
    ],
    bootstrap: [AppComponent]
})
export class AppModule {}
