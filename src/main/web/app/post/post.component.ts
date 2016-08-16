/**
 * Created by jake on 2016. 8. 15..
 */

import {Component, OnInit} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {PostService} from "./post.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {Post} from "./post"; //<router-outlet></router-outlet> 아래에 셀렉터를 넣어주는 듯..

@Component({
    selector: 'post',
    templateUrl: 'app/post/post.component.html',
    directives: [CORE_DIRECTIVES, ROUTER_DIRECTIVES],
    providers: [PostService, HTTP_PROVIDERS]
})
export class PostComponent implements OnInit {

    private posts: Post[];
    private subscription;

    constructor(private postService: PostService) {
    }

    ngOnInit() {
        this.getPosts();
    }

    getPosts() {
        this.subscription = this.postService.getPosts()
            .subscribe(
            (data) => {
                console.log("getPosts", data.json());
                this.posts = data.json().posts;
            },
            (err) => console.log(err),
            () => console.log('post service test complete')
        );
    }

    selectPost(post) {
        console.log("select post", post);
    }

    deletePost(post) {
        this.postService.deletePost(post);
        this.posts.slice(post);
    }
}