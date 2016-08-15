/**
 * Created by jake on 2016. 8. 15..
 */

import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {PostService} from './post.service';
import {Observable} from "rxjs";
import {HTTP_PROVIDERS} from "@angular/http";

@Component({
    selector: 'post',
    templateUrl: 'app/post/post.component.html',
    directives: [CORE_DIRECTIVES, ROUTER_DIRECTIVES],
    providers: [PostService, HTTP_PROVIDERS]
})
export class PostComponent implements OnInit {

    posts: Observable<Post[]>;

    constructor(private postService: PostService) {}

    ngOnInit(): void {
        this.getPosts();
    }

    getPosts() {
        this.posts = this.postService.getPosts();
    }
}

export class Post {
    id: number;
    title: string;
    content: string;
}