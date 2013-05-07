declare namespace cv="http://www.univ-rouen.fr/cv";
<result ageMoyen ="{avg(collection("cv-base")/cv:cv/cv:Civilite/cv:Age/text())}" nbCandidat="{count(collection("cv-base")/cv:cv)}">{
for $c in collection("cv-base")/cv:cv
order by $c/cv:Civilite/cv:Prenom/text()
return <candidat> {string-join(($c/cv:Civilite/cv:Prenom/text(),$c/cv:Civilite/cv:Nom/text()), ' ')} - {$c/cv:Titre/text()}</candidat> 
}</result>