/**
 * Created by jake on 2016. 8. 15..
 */

import {Injectable, Inject} from '@angular/core';
import {Http, Response} from '@angular/http';

@Injectable()
export class PostService {
    constructor(@Inject(Http) private http: Http) {}

    private postsUrl = "/posts"
    getPosts() {
        return this.http.get(this.postsUrl)
            .map((res:Response) => res.json());
    }
}