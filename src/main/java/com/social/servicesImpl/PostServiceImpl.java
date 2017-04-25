package com.social.servicesImpl;

import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import com.social.models.viewModels.PostViewModel;
import com.social.repositories.CommentRepository;
import com.social.repositories.PostRepository;
import com.social.services.CommentService;
import com.social.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(PostCreationModel postModel, User currentUser) {
        Post post = modelMapper.map(postModel, Post.class);
        post.setAuthor(currentUser);
        postRepository.save(post);
    }

    @Override
    public List<PostViewModel> index() {
        Iterable<Post> posts = postRepository.findAll();
        List<PostViewModel> postViews = new ArrayList<>();
        for(Post post : posts) {
            PostViewModel postViewModel = new PostViewModel();
            postViewModel.setContent(post.getContent());
            postViewModel.setUsername(post.getAuthor().getUsername());
            postViewModel.setId(post.getId());
            postViewModel.setComments(commentService.getCommentsOfPost(post));
            postViews.add(postViewModel);
        }
        return postViews;
    }
}
