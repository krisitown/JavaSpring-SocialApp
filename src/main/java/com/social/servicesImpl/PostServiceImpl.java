package com.social.servicesImpl;

import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.PostCreationModel;
import com.social.repositories.PostRepository;
import com.social.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(PostCreationModel postModel, User currentUser) {
        Post post = modelMapper.map(postModel, Post.class);
        post.setAuthor(currentUser);
        postRepository.save(post);
    }

    @Override
    public Iterable<Post> index() {
        return postRepository.findAll();
    }
}
