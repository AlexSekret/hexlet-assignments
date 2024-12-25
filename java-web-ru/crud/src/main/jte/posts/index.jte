@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes;
@param PostsPage page
@param Integer pageNumber
@param Integer maxPageCount
@template.layout.page(
    content = @`
       <nav aria-label="Page navigation example">
         <ul class="pagination">
           <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=@if(pageNumber > 1)${pageNumber - 1}@endif">Previous</a></li>
           <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=@if(pageNumber < maxPageCount)${pageNumber + 1}@endif">Next</a></li>
         </ul>
       </nav>
       @for(var post : page.getPosts())
         <li>
             <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
         </li>
       @endfor
    `
)