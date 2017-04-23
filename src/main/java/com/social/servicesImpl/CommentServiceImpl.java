package com.social.servicesImpl;

import com.social.entities.Comment;
import com.social.entities.User;
import com.social.models.bindingModels.CommentCreationModel;
import com.social.repositories.CommentRepository;
import com.social.repositories.PostRepository;
import com.social.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(CommentCreationModel commentModel, User currentUser) {
        Comment comment = modelMapper.map(commentModel, Comment.class);
        comment.setAuthor(currentUser);
        comment.setOriginPost(postRepository.findOne(commentModel.getOriginPostId()));
        commentRepository.save(comment);
    }
}
