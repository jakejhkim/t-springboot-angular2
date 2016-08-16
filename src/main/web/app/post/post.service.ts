/**
 * Created by jake on 2016. 8. 15..
 */

import {Injectable, Inject} from "@angular/core";
import {Http, Response, RequestOptions, Headers} from "@angular/http";
import {Logger} from "../logger.service";
import "rxjs/add/operator/map";

@Injectable()
export class PostService {
    constructor(@Inject(Http) private http: Http,
                private logger: Logger) {
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
    }

    private postsUrl = "/api/posts";
    private headers: Headers;

    getPosts() {
        return this.http.get(this.postsUrl);
        //     .map((res:Response) => {         // map 의 쓰임새를 알아보자
        //     this.logger.log(res.json());
        //     res.json();
        // });
    };

    createPost(post) {
        let options = new RequestOptions({headers: this.headers});
        let body = JSON.stringify(post);
        console.log("Create post", body);
        return this.http.post(this.postsUrl, body, options).map((res: Response) => {
            this.getPosts();
            res.json();
        });
    }

    updatePost(post) {
        let options = new RequestOptions({headers: this.headers});
        let body = JSON.stringify(post);
        console.log("Update post", body);
        return this.http.put(this.postsUrl + "/" + post.id, body, options).map((res: Response) => res.json());
    }

    deletePost(post) {
        let options = new RequestOptions({headers: this.headers});
        console.log("Delete post", post);
        return this.http.delete(this.postsUrl + "/" + post.id).map((res: Response) => res.json());
    }
}