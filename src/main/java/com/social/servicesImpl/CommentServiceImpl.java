package com.social.servicesImpl;

import com.social.entities.Comment;
import com.social.entities.Post;
import com.social.entities.User;
import com.social.models.bindingModels.CommentCreationModel;
import com.social.models.viewModels.CommentViewModel;
import com.social.repositories.CommentRepository;
import com.social.repositories.PostRepository;
import com.social.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CommentViewModel> getCommentsOfPost(Post post) {
        List<CommentViewModel> commentModels = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentViewModel commentViewModel = modelMapper.map(comment, CommentViewModel.class);
            commentViewModel.setUsername(comment.getAuthor().getUsername());
            commentModels.add(commentViewModel);
        }
        return commentModels;
    }
}
