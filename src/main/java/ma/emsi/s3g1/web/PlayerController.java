package ma.emsi.s3g1.web;

import ma.emsi.s3g1.entities.Player;
import ma.emsi.s3g1.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;



    @GetMapping(path="/index")
    public String allPlayers(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name="search", defaultValue = "") String searchName)
    {

        //Page<Student> pageStudents = studentRepository.findAll(PageRequest.of(page,size));

        Page<Player> pagePlayers = playerRepository.findByFullNameContains(searchName, PageRequest.of(page,size));

        int[] pages = new int[pagePlayers.getTotalPages()];
        for(int i=0; i<pages.length; i++)
            pages[i]=i;


        model.addAttribute("pagePlayers",pagePlayers.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "players";
    }
    @GetMapping(path="/create")
    public String createPlayer(Model model){
        Player player = new Player();
        model.addAttribute("player", player);
        return "formAddPlayer";

    }


    @PostMapping(path = "/save")
    public String savePlayer(Model model, Player s,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        playerRepository.save(s);
        return "redirect:/index?page="+page+"&size="+size+"&search="+search;
    }


    @GetMapping(path = "/")
    public String homePage(){
        return "/menu";
    }

    @GetMapping(path="/delete")
    public String deletePlayer(
            int page, int size, String search,
            @RequestParam(name="id") Integer id){
        playerRepository.deleteById(id);

        return "redirect:/index?page="+page+"&size="+size+"&search="+search;
    }


    @GetMapping(path = "/edit")
    public String editPlayer(Model model , int page, int size, String search, Integer id){
        Player player = playerRepository.findById(id).orElse(null);
        if(player == null) throw new RuntimeException("Erreur");
        model.addAttribute("player", player);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "formEditPlayer";



    }


}
