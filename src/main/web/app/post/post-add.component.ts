/**
 * Created by jake on 2016. 8. 15..
 */

import {PostService} from "./post.service";
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from "@angular/common";
import {Component} from "@angular/core";
import {Logger} from "../logger.service";
import {Post} from "./post";

@Component({
    selector: "add-post",
    templateUrl: 'app/post/post-add.component.html',
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES],
    providers: [PostService]
})
export class PostAddComponent {
    submitted = false;
    post = new Post("", "");

    constructor(private logger: Logger,
                private postService: PostService) {
    }

    onSubmit() {
        this.submitted = true;
        this.logger.log(this.post);
        this.postService.createPost(this.post).subscribe(
            (data) => {
                console.log("Create post", data);
            },
            (err) => console.log(err),
            () => console.log('Add Post test complete')
        );
    }
}