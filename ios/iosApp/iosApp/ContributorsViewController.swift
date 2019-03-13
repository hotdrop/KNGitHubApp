import UIKit
import common

class ContributorsViewController: UITableViewController {

    var itemArr: [ItemContributor] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setUpNavigation()
        onLoadContributors()
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itemArr.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ContributorItemCell", for: indexPath)
        let item = itemArr[indexPath.row]
        
        let imageView = cell.viewWithTag(1) as! UIImageView
        let url = URL(string: item.avatarUrl)
        do {
            let data = try Data(contentsOf: url!)
            imageView.image = UIImage(data: data)
            imageView.layer.cornerRadius = 20.0
            imageView.layer.masksToBounds = true
        } catch let err {
            print("Error: \(err.localizedDescription)")
        }
        
        let nameLabel = cell.viewWithTag(2) as! UILabel
        nameLabel.text = item.name
        
        let countLabel = cell.viewWithTag(3) as! UILabel
        countLabel.text = String(item.count)
        
        return cell
    }
    
    func onLoadContributors() {
        let useCase = ContributorUseCaseForIOS()
        useCase.findAll {
            $0.forEach {
                let contributor: Contributor = $0
                print("   contributor name = \(contributor.name)")
                let item = self.toItem(contributor)
                self.itemArr.append(item)
            }
            self.tableView.reloadData()
            return .init()
        }
    }
    
    func setUpNavigation() {
        navigationItem.title = "Contributors"
        self.navigationController?.navigationBar.barTintColor = #colorLiteral(red: 0.2588235438, green: 0.7568627596, blue: 0.9686274529, alpha: 1)
        self.navigationController?.navigationBar.prefersLargeTitles = true
        
        self.navigationController?.navigationBar.isTranslucent = false
    }
    
    private func toItem(_ contributor: Contributor) -> ItemContributor {
        return ItemContributor(
            name: contributor.name,
            avatarUrl: contributor.avatarUrl,
            count: contributor.contribution)
    }
    
    class ItemContributor {
        var name: String
        var avatarUrl: String
        var count: Int32
        
        init(name: String, avatarUrl: String, count: Int32) {
            self.name = name
            self.avatarUrl = avatarUrl
            self.count = count
        }
    }
}

